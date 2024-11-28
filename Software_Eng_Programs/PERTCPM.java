import java.util.*;

class Task {
    String name;
    double optimisticTime;
    double mostLikelyTime;
    double pessimisticTime;
    double expectedTime;
    List<Task> dependencies = new ArrayList<>();

    Task(String name, double o, double m, double p) {
        this.name = name;
        this.optimisticTime = o;
        this.mostLikelyTime = m;
        this.pessimisticTime = p;
        this.expectedTime = (o + 4 * m + p) / 6;
    }
}

public class PERTCPM {
    static Map<String, Task> tasks = new HashMap<>();
    static Map<Task, Integer> inDegree = new HashMap<>();
    static Map<Task, Double> earliestFinish = new HashMap<>();
    static Map<Task, Double> latestFinish = new HashMap<>();

    public static void main(String[] args) {
        // Example input: tasks with dependencies
        addTask("A", 1, 2, 3);
        addTask("B", 2, 3, 4);
        addTask("C", 1, 3, 5);
        addTask("D", 2, 4, 6);
        addDependency("B", "A");
        addDependency("C", "A");
        addDependency("D", "B");
        addDependency("D", "C");

        computeCriticalPath();
    }

    public static void addTask(String name, double o, double m, double p) {
        Task task = new Task(name, o, m, p);
        tasks.put(name, task);
        inDegree.put(task, 0);
    }

    public static void addDependency(String taskName, String dependencyName) {
        Task task = tasks.get(taskName);
        Task dependency = tasks.get(dependencyName);
        if (task != null && dependency != null) {
            task.dependencies.add(dependency);
            inDegree.put(dependency, inDegree.get(dependency) + 1);
        }
    }

    public static void computeCriticalPath() {
        // Topological sort
        Queue<Task> queue = new LinkedList<>();
        Map<Task, Double> earliestStart = new HashMap<>();
        for (Task task : inDegree.keySet()) {
            if (inDegree.get(task) == 0) queue.add(task);
            earliestStart.put(task, 0.0);
        }

        while (!queue.isEmpty()) {
            Task current = queue.poll();
            double finishTime = earliestStart.get(current) + current.expectedTime;
            earliestFinish.put(current, finishTime);

            for (Task dependency : current.dependencies) {
                double newStart = Math.max(earliestStart.getOrDefault(dependency, 0.0), finishTime);
                earliestStart.put(dependency, newStart);
                inDegree.put(dependency, inDegree.get(dependency) - 1);
                if (inDegree.get(dependency) == 0) queue.add(dependency);
            }
        }

        // Backward pass
        double projectFinishTime = Collections.max(earliestFinish.values());
        for (Task task : tasks.values()) {
            latestFinish.put(task, projectFinishTime);
        }

        for (Task task : earliestFinish.keySet()) {
            for (Task dependency : task.dependencies) {
                double dependencyLatestFinish = latestFinish.get(task) - dependency.expectedTime;
                latestFinish.put(dependency, Math.min(latestFinish.get(dependency), dependencyLatestFinish));
            }
        }

        // Display Results
        System.out.println("Task Details:");
        for (Task task : tasks.values()) {
            double slack = latestFinish.get(task) - earliestFinish.get(task);
            System.out.println("Task: " + task.name + ", TE: " + task.expectedTime + ", Slack: " + slack);
        }
    }
}

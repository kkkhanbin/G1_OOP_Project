package management;

public class NewsSubscriber implements Observer {
    private String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(News news) {
        System.out.println(name + " received news notification: " + news.getTitle());
    }

    @Override
    public String toString() {
        return "NewsSubscriber{" +
                "name='" + name + '\'' +
                '}';
    }
}
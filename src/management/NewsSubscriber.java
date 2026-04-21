package management;

public class NewsSubscriber implements Observer {
    private String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(News news) {
        System.out.println(name + " received news: " + news.getTitle());
    }
}
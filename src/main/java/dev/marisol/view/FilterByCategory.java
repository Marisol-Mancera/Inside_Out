package dev.marisol.view;

public class FilterByCategoryView {
    private final Scanner scanner;
    private final AddMomentView view;

    public FilterByCategoryView(Scanner scanner) {
        this.scanner = scanner;
        this.view = new AddMomentView(scanner);
    }

    public boolean filterCategory() {
        return view.askIsPositive();
    }
}

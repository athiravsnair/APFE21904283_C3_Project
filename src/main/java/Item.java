public class Item {
    private String name;
    private int price;
    private boolean isSelected;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }

    public int getPrice(){
        return this.price;
    }

    public void selectItem(boolean select){
        this.isSelected=select;
    }

    public boolean itemIsSelected(){
        return isSelected;
    }
}

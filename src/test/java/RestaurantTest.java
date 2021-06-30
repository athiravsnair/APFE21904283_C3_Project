import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE
    public static Restaurant restaurant = null;

    @BeforeAll
    public static void init() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",100);
        restaurant.addToMenu("Vegetable lasagne", 350);
        restaurant.addToMenu("Clam Chowder",300);
        restaurant.addToMenu("Club Sandwich", 200);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant openRestaurant = Mockito.spy(restaurant);
        LocalTime currentTime = LocalTime.now().minusHours(1);

        Mockito.when(openRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertTrue(openRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant closedRestaurant = Mockito.spy(restaurant);

        LocalTime currentTime = LocalTime.now().minusHours(10);

        Mockito.when(closedRestaurant.getCurrentTime()).thenReturn(currentTime);

        assertFalse(closedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

     /* When items are selected from the menu
        the prices should be added
        and the total cost must be displayed
     */
    @Test
    public void when_items_are_selected_from_the_menu_prices_should_be_added_to_display_the_order_total(){
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        List<Item> menu = spiedRestaurant.getMenu();

        //selecting items at odd places in the menu for testing
        for(int i=0;i<menu.size();i+=2){
            menu.get(i).selectItem(true);
        }

        assertEquals(400,spiedRestaurant.totalCost());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
import com.example.knightmove.Model.Question;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class questionTest {
    @Test
    public void getterSetterTest(){
        ArrayList <String> list = new ArrayList<>();
        list.add("good");
        list.add("amazing");
        list.add("bad");
        list.add("worse");
        Question q = new Question("How you doin",list,1,1,"Panda");
        assertEquals(q.getQuestion(),"how you doin");


    }
}

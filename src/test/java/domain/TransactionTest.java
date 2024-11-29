package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TransactionTest { //테스트 드라이버
   
    @Test
    public void testTeansactionCreation(){
        //1. 셋업(실행을 위한 준비 과정) + 실행 
        Transaction t1 = new Transaction("deposit", 100);

        //3. 확인 - 잘 작동되는지 확인하는 과정
        assertNotNull(t1); //t1이 null이 아닌지 확인 
        assertEquals("deposit", t1.getTransactionType()); //(예상값=deposit, 실제값=t1.getTransactionType()이 같은지 확인)
        assertEquals(100, t1.getAmount());
    }

    @Test
    public void testTeansactionToString(){
        //1. 셋업(실행을 위한 준비 과정) + 실행 
        Transaction t1 = new Transaction("deposit", 100);

        //2. 실행
        String result = t1.toString();

        //3. 확인 - 잘 작동되는지 확인하는 과정
        assertEquals("Transaction: deposit, Amount: 100.0", result);
    }
}

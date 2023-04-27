package qiitastudy;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Sandbox06ExTest {

	// MockitoExtensionを使うと MockitoAnnotations.openMocksを使わなくてよくなる
	// mockito-junit-jupiterが必要

	@Mock
	protected List<String> mockList;
	
	
	@Test
	void test1() throws Exception {
		// フィールドの@Mockを使う
        when(mockList.get(0)).thenReturn("mocked");
        System.out.println("mockList.get(0) = " + mockList.get(0));
	}

    @Test
    void test2(@Mock Map<String, String> mockMap) {
    	// @Mockを引数として受け取ることもできる。
        when(mockMap.get("hello")).thenReturn("mocked");
        System.out.println("mockMap.get(hello) = " + mockMap.get("hello"));
    }

}

package qiitastudy;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

// https://qiita.com/opengl-8080/items/dcdf2cb14f2642240a5b#%E3%82%B9%E3%83%91%E3%82%A4
public class Sandbox04Test {

	@Test
	public void test1() throws Exception {
		Hoge hoge = new Hoge();
		// スパイのインスタンスを作る
		// hogeのコピーで作られるがシャローコピー？なので
		// listに変更があるものは両方のインスタンスに影響がでる
		// nameへの変更は片方のみ
		Hoge spy = Mockito.spy(hoge);

		// sizeメソッドだけ書き換え
		Mockito.when(spy.size()).thenReturn(100);

		spy.add("hello");
		spy.add("world");
		spy.setName("Spied!");

		System.out.println("spy       = " + spy);
		System.out.println("spy.size  = " + spy.size());//100が返される
		System.out.println("hoge      = " + hoge);//nameはnull
		System.out.println("hoge.size = " + hoge.size());
	}

	public static class Hoge implements Cloneable {
		private List<String> list = new ArrayList<>();
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public void add(String value) {
			list.add(value);
		}

		public int size() {
			return list.size();
		}

		@Override
		public String toString() {
			return "Hoge{" + "list=" + list + ", name='" + name + '\'' + '}';
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			// 落ちてないのでcloneでコピーされているわけではない
			throw new CloneNotSupportedException();
		}
	}
	
	//----------------------

	// https://qiita.com/opengl-8080/items/dcdf2cb14f2642240a5b#%E5%AE%9F%E9%9A%9B%E3%81%AE%E3%83%A1%E3%82%BD%E3%83%83%E3%83%89%E3%82%92%E5%91%BC%E3%81%B0%E3%81%9A%E3%81%AB%E3%82%B9%E3%82%BF%E3%83%96%E5%8C%96%E3%81%99%E3%82%8B
	@Test
	void test2() throws Exception {
		List<String> list = new ArrayList<>();
		List<String> spy = spy(list);

		// spyに対してこれをするとget(0)を呼んだ時に 実際のget(0)が実行されて
		// listの中の配列はサイズ0なのでおちる
		// Mockito.when(spy.get(0)).thenReturn("spied");

		// なのでdoReturnにする
		Mockito.doReturn("spied").when(spy).get(0);

		System.out.println("spy.get(0) = " + spy.get(0));
	}
	
	@Test
	void test3() throws Exception {
		// abstractのクラスでもモック化できる
		AbstractClass mock = Mockito.spy(AbstractClass.class);
        System.out.println("mock.concreteMethod()       = " + mock.concreteMethod());
        
        // 例外は起きずにStringのデフォルト(null)が返ってくる
        System.out.println("mock.abstractStringMethod() = " + mock.abstractStringMethod());
	}

    public static abstract class AbstractClass {

        public String concreteMethod() {
            return "concreteMethod";
        }

        abstract public String abstractStringMethod();
    }
	//----------------------
    
    
    @Test
	void test4() throws Exception {
    	// 継承関係になくても委譲させる事ができるらしい
    	// finalクラスとかに使えたり
    	Test4Hoge mock = Mockito.mock(Test4Hoge.class, AdditionalAnswers.delegatesTo(new Test4HogeFinalClass()));
    	
    	Mockito.when(mock.world()).thenReturn("mocked");

        System.out.println(mock.hello(10));
        System.out.println(mock.world());
	}
    
    public interface Test4Hoge {
        String hello(int n);
        String world();
    }

    public final static class Test4HogeFinalClass {
        public String hello() {
            return "hello()";
        }

        public String hello(int n) {
            return "hello(" + n + ")";
        }

        public String world() {
            return "world()";
        }
    }
    
}

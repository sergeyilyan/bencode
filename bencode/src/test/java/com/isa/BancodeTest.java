package com.isa;

import java.io.IOException;

import com.isa.bencode.Bencode;
import com.isa.bencode.Bytes;
import com.isa.bencode.Map;
import com.isa.bencode.Number;
import com.isa.bencode.Object;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BancodeTest extends TestCase {
	public BancodeTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(BancodeTest.class);
	}

	public void test1() throws IOException {
		Map map = new Map();
		map.getValue().put(new Bytes("bar".getBytes()),
				new Bytes("spam".getBytes()));
		map.getValue().put(new Bytes("foo".getBytes()), new Number(42L));
		assertTrue(new String(Bencode.encode(map))
				.equals("d3:bar4:spam3:fooi42ee"));
	}

	public void test2() throws IOException {
		Object object = Bencode.decode("l3:bar4:spam3:fooi42ee".getBytes());
		assertTrue("[bar, spam, foo, 42]".equals(object.toString()));
	}

	public void test3() throws IOException {
		Object object = Bencode.decode("d3:bar4:spam3:fooi42ee".getBytes());
		assertTrue(new String(Bencode.encode(object)).equals("d3:bar4:spam3:fooi42ee"));
	}

	public void test4() throws IOException {
		Object object = Bencode.decode("i-43e".getBytes());
		assertTrue(object.equals(new Number(-43L)));
	}
	
	public void test5() throws IOException {
		Object object = Bencode.decode("i-43e".getBytes());
		assertTrue(new String(Bencode.encode(object)).equals("i-43e"));
	}
}

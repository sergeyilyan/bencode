package com.isa.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Number extends Object{
	private Long value;
	
	
	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Number(Long value) {
		this.value = value;
	}
	
	public Number(InputStream inputStream,char before) throws IOException {
		if(before!='i')throw new RuntimeException();
		char c;
		StringBuffer length = new StringBuffer();
		while((c = (char)inputStream.read())!='e'){
			length.append(c);
		}
		value = Long.valueOf(length.toString());
	}

	@Override
	public void writeExternal(OutputStream outputStream) throws IOException {
		outputStream.write( ("i"+value+"e").getBytes("UTF-8"));
	}
	
	@Override
	public String toString() {
		return (value!=null)?value.toString():null;
	}
	

	@Override
	public boolean equals(java.lang.Object arg0) {
		if(arg0 instanceof Number){
			return value.equals(((Number)arg0).value);
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
}

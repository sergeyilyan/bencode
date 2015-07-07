package com.isa.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class Array extends Object{
	List<Object> value;

	public Array(List<Object> value) {
		this.value = value;
	}
	
	public Array(InputStream inputStream,char before) throws IOException {
		if(before!='l')throw new RuntimeException();
		this.value=new LinkedList<Object>();
		char c;
		while((c = (char)inputStream.read())!='e'){
			Object value = Object.create(inputStream,c);
			this.value.add(value);
		}
	}

	@Override
	public void writeExternal(OutputStream outputStream) throws IOException {
		outputStream.write('l');
		for(Object value: this.value){
			value.writeExternal(outputStream);
		}
		outputStream.write('e');
	}
	
	
	@Override
	public String toString() {
		return (value!=null)?value.toString():null;
	}

	@Override
	public boolean equals(java.lang.Object arg0) {
		if(arg0 instanceof Array){
			return value.equals(((Array)arg0).value);
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
}

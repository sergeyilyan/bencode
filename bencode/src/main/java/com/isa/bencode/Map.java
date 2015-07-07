package com.isa.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Map extends Object{
	private java.util.Map<Object, Object> value;

	public java.util.Map<Object, Object> getValue() {
		return value;
	}
	public void setValue(java.util.Map<Object, Object> value) {
		this.value = value;
	}
	public Map(java.util.Map<Object, Object> value) {
		this.value = value;
	}
	public Map() {
		this.value = new LinkedHashMap<Object,Object>();
	}
	
	public Map(InputStream inputStream,char before) throws IOException {
		if(before!='d')throw new RuntimeException();
		this.value=new HashMap<Object,Object>();
		char c;
		while((c = (char)inputStream.read())!='e'){
			Object key = Object.create(inputStream,c);
			c= (char)inputStream.read();
			Object value = Object.create(inputStream,c);
			this.value.put(key, value);
		}
	}

	@Override
	public void writeExternal(OutputStream outputStream) throws IOException {
		outputStream.write('d');
		for(java.util.Map.Entry<Object,Object> entry: value.entrySet()){
			entry.getKey().writeExternal(outputStream);
			entry.getValue().writeExternal(outputStream);
		}
		outputStream.write('e');
	}
	@Override
	public String toString() {
		return (value!=null)?value.toString():null;
	}

	@Override
	public boolean equals(java.lang.Object arg0) {
		if(arg0 instanceof Map){
			return value.equals(((Map)arg0).value);
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
		
}

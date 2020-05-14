package com.redbeemedia.enigma.exposureutils.query;

import com.redbeemedia.enigma.core.util.IStringAppendable;
import com.redbeemedia.enigma.core.util.UrlPath;
import com.redbeemedia.enigma.exposureutils.StringArray;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class QueryParameterBuilderTest {
    @Test
    public void testSetter() throws MalformedURLException {
        IQueryParameterSet mockSet = new MockQueryParameterSet();
        IQueryParameter<Integer> number = QueryParameterBuilder.create(mockSet,Integer.class, "number");

        UrlPath urlPath = new UrlPath("http://www.example.com/test?");
        number.setValue(13);
        Assert.assertEquals("http://www.example.com/test?number=13", applyTo(urlPath, number).toURL().toString());
        number.setValue(42);
        Assert.assertEquals("http://www.example.com/test?number=42", applyTo(urlPath, number).toURL().toString());
    }

    @Test
    public void testCreateWithDefaultValue() throws MalformedURLException {
        IQueryParameterSet mockSet = new MockQueryParameterSet();
        IQueryParameter<Integer> number = QueryParameterBuilder.create(mockSet,Integer.class, "number", 123);

        UrlPath urlPath = new UrlPath("http://www.example.com/test?");
        Assert.assertEquals("http://www.example.com/test?number=123", applyTo(urlPath, number).toURL().toString());
        number.setValue(42);
        Assert.assertEquals("http://www.example.com/test?number=42", applyTo(urlPath, number).toURL().toString());
    }

    @Test
    public void testCreateWithoutDefaultValue() throws MalformedURLException {
        IQueryParameterSet mockSet = new MockQueryParameterSet();
        IQueryParameter<Integer> number = QueryParameterBuilder.create(mockSet,Integer.class, "number");

        UrlPath urlPath = new UrlPath("http://www.example.com/test?");
        Assert.assertEquals("http://www.example.com/test?", applyTo(urlPath, number).toURL().toString());
        number.setValue(42);
        Assert.assertEquals("http://www.example.com/test?number=42", applyTo(urlPath, number).toURL().toString());
        number.setValue(1337);
        Assert.assertEquals("http://www.example.com/test?number=1337", applyTo(urlPath, number).toURL().toString());
    }

    @Test
    public void testWithStringArray() {
        IQueryParameterSet mockSet = new MockQueryParameterSet();
        IQueryParameter<StringArray> array = QueryParameterBuilder.create(mockSet,StringArray.class, "array");

        UrlPath urlPath = new UrlPath("http://www.example.com/test?");
        array.setValue(new StringArray());
        Assert.assertEquals("http://www.example.com/test?", applyTo(urlPath, array).toString());
        array.setValue(new StringArray("oneValue"));
        Assert.assertEquals("http://www.example.com/test?array=oneValue", applyTo(urlPath, array).toString());
        array.setValue(new StringArray("a","b","c","has space?"));
        Assert.assertEquals("http://www.example.com/test?array=a&array=b&array=c&array=has+space%3F", applyTo(urlPath, array).toString());
        array.setValue(new StringArray("a",null,"c"));
        Assert.assertEquals("http://www.example.com/test?array=a&array=null&array=c", applyTo(urlPath, array).toString());
        array.setValue(null); //Explicitly set to null
        Assert.assertEquals("http://www.example.com/test?array=null", applyTo(urlPath, array).toString());
    }

    @Test
    public void testWithSpecialQueryParameterType() {
        IQueryParameterSet mockSet = new MockQueryParameterSet();
        class MockType implements ISpecialQueryParameterType {
            private final String value;

            public MockType(String value) {
                this.value = value;
            }

            @Override
            public IStringAppendable appendTo(String name, IStringAppendable urlPath) {
                return urlPath.append(name).append("ish").append(":{"+value+"}");
            }
        }
        IQueryParameter<MockType> specialParameter = QueryParameterBuilder.create(mockSet,MockType.class, "special");

        UrlPath urlPath = new UrlPath("http://www.example.com/test?");
        Assert.assertEquals("http://www.example.com/test?", applyTo(urlPath, specialParameter).toString());
        specialParameter.setValue(new MockType("mockValue"));
        Assert.assertEquals("http://www.example.com/test?specialish:{mockValue}", applyTo(urlPath, specialParameter).toString());
    }

    private static UrlPath applyTo(UrlPath urlPath, IQueryParameter<?> queryParameter) {
        return (UrlPath) queryParameter.apply(urlPath);
    }

    private static class MockQueryParameterSet implements IQueryParameterSet {

        @Override
        public void add(IQueryParameter<?> queryParameter) {
        }

        @Override
        public UrlPath applyAll(UrlPath urlPath) {
            throw new UnsupportedOperationException();
        }
    }
}

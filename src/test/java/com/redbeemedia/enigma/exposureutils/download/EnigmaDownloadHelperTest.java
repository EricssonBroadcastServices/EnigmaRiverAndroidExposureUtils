package com.redbeemedia.enigma.exposureutils.download;

import android.util.JsonReader;

import com.redbeemedia.enigma.core.testutil.json.JsonArrayBuilder;
import com.redbeemedia.enigma.core.testutil.json.JsonObjectBuilder;
import com.redbeemedia.enigma.core.util.ISO8601Util;
import com.redbeemedia.enigma.exposureutils.models.asset.ApiAsset;
import com.redbeemedia.enigma.exposureutils.models.publication.ApiPublication;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

public class EnigmaDownloadHelperTest {
    @Test
    public void testIntersection() {
        Collection<String> animalsWithFourLegs = Arrays.asList("Lion","Cat", "Dog");
        Collection<String> suitablePets = Arrays.asList("Cat", "Dog", "Parrot");
        Collection<String> pirateRelated = Arrays.asList("Eyepatch", "Parrot");
        Collection<String> emptyCollection = Arrays.asList();

        Assert.assertEquals(false, EnigmaDownloadHelper.isIntersectionEmpty(animalsWithFourLegs, suitablePets));
        Assert.assertEquals(false, EnigmaDownloadHelper.isIntersectionEmpty(suitablePets, pirateRelated));
        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(animalsWithFourLegs, pirateRelated));

        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(animalsWithFourLegs, null));
        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(null, pirateRelated));
        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(null, null));

        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(animalsWithFourLegs, emptyCollection));
        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(emptyCollection, pirateRelated));

        Assert.assertEquals(true, EnigmaDownloadHelper.isIntersectionEmpty(emptyCollection, emptyCollection));
        Assert.assertEquals(false, EnigmaDownloadHelper.isIntersectionEmpty(animalsWithFourLegs, animalsWithFourLegs));
    }

    @Test
    public void testIsActive() {
        class MockParser implements ISO8601Util.IISO8601Parser {
            private Set<String> parseExceptionInducing = new HashSet<>();
            private Map<String, Long> values = new HashMap<>();
            @Override
            public long parse(String iso8601String) throws ParseException {
                if(parseExceptionInducing.contains(iso8601String)) {
                    throw new ParseException("Unit test", 0);
                }
                return values.get(iso8601String).longValue();
            }

            public void set(String id, long value) {
                values.put(id, value);
            }

            public void setParseExceptions(String ... ids) {
                parseExceptionInducing.clear();
                for(String id : ids) {
                    parseExceptionInducing.add(id);
                }
            }
        }

        MockParser parser = new MockParser();

        parser.set("from", 1000);
        parser.set("to", 2000);
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 2000, "from", "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1000, "from", "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1500, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2001, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 999, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 0, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 5000, "from", "to"));


        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 2000, null, "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1000, null, "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1500, null, "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2001, null, "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 999, null, "to"));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 0, null, "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 5000, null, "to"));


        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 2000, "from", null));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1000, "from", null));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 1500, "from", null));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 2001, "from", null));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 999, "from", null));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 0, "from", null));
        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 5000, "from", null));


        Assert.assertEquals(true, EnigmaDownloadHelper.isActive(parser, 3000, null, null));


        parser.setParseExceptions("from");
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2000, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 1000, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 1500, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2001, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 999, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 0, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 5000, "from", "to"));


        parser.setParseExceptions("to");
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2000, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 1000, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 1500, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 2001, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 999, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 0, "from", "to"));
        Assert.assertEquals(false, EnigmaDownloadHelper.isActive(parser, 5000, "from", "to"));
    }

    @Test
    public void testFilterPublications() throws IOException, ParseException {
        Random deterministicRandom = new Random(87357);

        Collection<String> userAvailabilityKeys = Collections.unmodifiableList(Arrays.asList("testProd", "freeProd", "testTVOD"));
        List<ApiPublication> publications = new ArrayList<>();
        long nowUtc = 3000;
        for(String timeSpan : Arrays.asList("past","now","future")) {
            long fromDate;
            long toDate;
            if("past".equals(timeSpan)) {
                fromDate = 1000;
                toDate = 2000;
            } else if("now".equals(timeSpan)) {
                fromDate = 1500;
                toDate = 4000;
            } else if("future".equals(timeSpan)) {
                fromDate = 3300;
                toDate = 3900;
            } else {
                throw new RuntimeException("Should never happen. Update unit test.");
            }
            for(String downloadBlockedValue : Arrays.asList("missing", "true", "false")) {
                Boolean downloadBlocked = null;
                if("true".equals(downloadBlockedValue)) {
                    downloadBlocked = true;
                } else if("false".equals(downloadBlockedValue)) {
                    downloadBlocked = false;
                } else {
                    Assert.assertEquals("missing", downloadBlockedValue);
                }
                for(String productsValue : Arrays.asList("one matching", "several matching","none matching")) {
                    List<String> products = new ArrayList<>();
                    if("one matching".equals(productsValue)) {
                        products.add(pickRandom(deterministicRandom, userAvailabilityKeys));
                        if(deterministicRandom.nextBoolean()) {
                            products.add("extraProduct");
                        }
                    } else if("several matching".equals(productsValue)) {
                        products.addAll(userAvailabilityKeys);
                        products.remove(pickRandom(deterministicRandom, userAvailabilityKeys));
                        if(deterministicRandom.nextBoolean()) {
                            products.add("extraProduct");
                        }
                    } else {
                        Assert.assertEquals("none matching", productsValue);
                        products.add("specialProduct");
                        if(deterministicRandom.nextBoolean()) {
                            products.add("additionalProduct");
                        }
                    }
                    Collections.shuffle(products, deterministicRandom);
                    publications.add(createMockPublication(fromDate, toDate, downloadBlocked, products.toArray(new String[products.size()])));
                }
            }
        }

        Assert.assertEquals(27, publications.size());

        EnigmaDownloadHelper.filterPublications(publications, nowUtc, userAvailabilityKeys);

        Assert.assertEquals(6 , publications.size());

        ISO8601Util.IISO8601Parser parser = ISO8601Util.newParser();
        int withDownloadBlockedTrue = 0;
        int withDownloadBlockedFalse = 0;
        for(ApiPublication publication : publications) {
            Assert.assertEquals(1500, parser.parse(publication.getFromDate()));
            Assert.assertEquals(4000, parser.parse(publication.getToDate()));
            if(publication.getRights().getDownloadBlocked()) {
                withDownloadBlockedTrue++;
            } else {
                withDownloadBlockedFalse++;
            }
            boolean atLeastOneAvailabilityKeyMatch = false;
            findOneMatch: for(String availabilityKey : publication.getAvailabilityKeys()) {
                if(userAvailabilityKeys.contains(availabilityKey)) {
                    atLeastOneAvailabilityKeyMatch = true;
                    break findOneMatch;
                }
            }
            Assert.assertEquals(true, atLeastOneAvailabilityKeyMatch);
        }
        Assert.assertEquals(4, withDownloadBlockedFalse);
        Assert.assertEquals(2, withDownloadBlockedTrue);
    }


    @Test
    public void testIsAvailableToDownload_noPublications() throws IOException {

        Collection<String> userAvailabilityKeys = Collections.unmodifiableList(Arrays.asList("testProd", "freeProd", "testTVOD"));
        long nowUtcMillis = 10000;

        ApiAsset assetWithNoPublications;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            assetWithNoPublications = parseAsset(assetBuilder.toString());
        }

        ApiAsset assetWithNullPublications;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            assetWithNullPublications = parseAsset(assetBuilder.toString());
        }

        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithNoPublications, nowUtcMillis, userAvailabilityKeys));
        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithNullPublications, nowUtcMillis, userAvailabilityKeys));
    }

    @Test
    public void testIsAvailableToDownload_noMatchingPublications() throws IOException {
        Collection<String> userAvailabilityKeys = Collections.unmodifiableList(Arrays.asList("testProd", "freeProd", "testTVOD"));
        long nowUtcMillis = 10000;

        ApiAsset assetWithPublicationsOutsideNow_NoBlock;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000, 9000, false, "testProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(10500, 90000, false, "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000000, 910000000, false, "testTVOD"));
            Assert.assertEquals(3, publicationsBuilder.getJsonArray().length());
            assetWithPublicationsOutsideNow_NoBlock = parseAsset(assetBuilder.toString());
        }

        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithPublicationsOutsideNow_NoBlock, nowUtcMillis, userAvailabilityKeys));

        ApiAsset assetWithPublicationsOutsideNow_AllBlock;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000, 9000, true, "testProd", "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(10500, 90000, true, "testTVOD"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000000, 910000000, true, "frog"));
            Assert.assertEquals(3, publicationsBuilder.getJsonArray().length());
            assetWithPublicationsOutsideNow_AllBlock = parseAsset(assetBuilder.toString());
        }

        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithPublicationsOutsideNow_AllBlock, nowUtcMillis, userAvailabilityKeys));
    }

    @Test
    public void testIsAvailableToDownload_someMatchingPublications() throws IOException {
        Collection<String> userAvailabilityKeys = Collections.unmodifiableList(Arrays.asList("testProd", "freeProd", "testTVOD"));
        long nowUtcMillis = 10000;

        ApiAsset assetWithMatchingPublicationsButBlocked;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000, 9000, false, "testProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(10500, 90000, false, "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(5000, 15000, true, "testTVOD"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(4000, 11000, true, "testProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000000, 910000000, false, "freeProd"));
            Assert.assertEquals(5, publicationsBuilder.getJsonArray().length());
            assetWithMatchingPublicationsButBlocked = parseAsset(assetBuilder.toString());
        }

        ApiAsset assetWithMatchingPublicationsOneNotBlockedButNoMatchingKeys;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000, 9000, true, "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(10500, 90000, false, "frog", "cow"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(5000, 15000, true, "frog", "cow"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(4000, 11000, false, "cat"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000000, 910000000, false, "testTVOD"));
            Assert.assertEquals(5, publicationsBuilder.getJsonArray().length());
            assetWithMatchingPublicationsOneNotBlockedButNoMatchingKeys = parseAsset(assetBuilder.toString());
        }

        ApiAsset availableAsset;
        {
            JsonObjectBuilder assetBuilder = new JsonObjectBuilder();
            JsonArrayBuilder publicationsBuilder = assetBuilder.putArray("publications");
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000, 9000, true, "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(10500, 90000, false, "frog", "cow"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(5000, 15000, true, "frog", "cow"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(4000, 11000, false, "cat", "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(4000, 11000, true, "cat", "freeProd"));
            publicationsBuilder.getJsonArray().put(createMockPublicationJson(1000000, 910000000, false, "wolf"));
            Assert.assertEquals(6, publicationsBuilder.getJsonArray().length());
            availableAsset = parseAsset(assetBuilder.toString());
        }

        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithMatchingPublicationsButBlocked, nowUtcMillis, userAvailabilityKeys));
        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(assetWithMatchingPublicationsOneNotBlockedButNoMatchingKeys, nowUtcMillis, userAvailabilityKeys));
        Assert.assertEquals(true, EnigmaDownloadHelper.isAvailableToDownload(availableAsset, nowUtcMillis, userAvailabilityKeys));

        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(availableAsset, nowUtcMillis, Arrays.asList()));
        Assert.assertEquals(false, EnigmaDownloadHelper.isAvailableToDownload(availableAsset, 100000000, userAvailabilityKeys));
    }

    private static <T> T pickRandom(Random random, Collection<T> collection) {
        int pickedIndex = random.nextInt(collection.size());
        Iterator<T> iterator = collection.iterator();
        while(iterator.hasNext()) {
            T value = iterator.next();
            if(pickedIndex-- == 0) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    private static ApiAsset parseAsset(String jsonString) throws IOException {
        return new ApiAsset(new JsonReader(new StringReader(jsonString)));
    }

    private static JSONObject createMockPublicationJson(long fromDate, long toDate, Boolean downloadBlocked, String ... availabilityKeys) {
        ISO8601Util.IISO8601Writer iso8601Writer = ISO8601Util.newWriter(TimeZone.getTimeZone("UTC"));
        JsonObjectBuilder publication = new JsonObjectBuilder();
        JsonArrayBuilder availabilityKeysBuilder = publication.putArray("availabilityKeys");
        for(String availabilityKey : availabilityKeys) {
            availabilityKeysBuilder.add(availabilityKey);
        }
        JsonObjectBuilder rights = publication.putObject("rights");
        if(downloadBlocked != null) {
            rights.put("downloadBlocked", downloadBlocked);
        }
        publication.put("fromDate", iso8601Writer.toIso8601(fromDate));
        publication.put("toDate", iso8601Writer.toIso8601(toDate));
        return publication.getJsonObject();
    }


    private static ApiPublication createMockPublication(long fromDate, long toDate, Boolean downloadBlocked, String ... availabilityKeys) throws IOException {
        JSONObject jsonObject = createMockPublicationJson(fromDate, toDate, downloadBlocked, availabilityKeys);
        return new ApiPublication(new JsonReader(new StringReader(jsonObject.toString())));
    }
}

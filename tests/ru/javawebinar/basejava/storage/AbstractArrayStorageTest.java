package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    Storage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";


//    protected AbstractArrayStorageTest(Storage storage) {
//        this.storage = storage;
//    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = storage.get("uuid1");
        r.setUuid("uuid_0");
        storage.update(r);
        Assert.assertNull(storage.get("uuid1"));
        Assert.assertEquals(storage.get("uuid_0"), r);
    }

    @Test
    public void getAll() {
        Resume[] new_storage = storage.getAll();
        Assert.assertEquals(storage.size(), new_storage.length);
        Assert.assertEquals(new_storage[0], storage.get(UUID_1));
        Assert.assertEquals(new_storage[1], storage.get(UUID_2));
        Assert.assertEquals(new_storage[2], storage.get(UUID_3));
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertNull(storage.get(UUID_1));
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() {
        Resume r = storage.get(UUID_1);
        Assert.assertEquals(UUID_1, r.getUuid());
    }

    @Test
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }

        storage.save(new Resume("new_uuid"));

    }

}
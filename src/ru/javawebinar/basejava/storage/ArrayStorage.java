package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[1000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void update(Resume r) {

        int index = getIndex(r.getUuid());
        if (index >= 0) { //chek if resume is present
            storage[index] = r;
        } else {
            System.out.println("resume " + r.getUuid() + " is not exist");
        }
    }


    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("resume " + r.getUuid() + " already exist");
        } else if (size == storage.length) {
            System.out.println("storage is overflow");
        } else {
            storage[size] = r;
            size++;
        }

    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("resume " + uuid + " not exist");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) { //chek if resume is present
            System.out.println("resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] newStorage = new Resume[size];
        int counter = 0;
        for (int i = 0; i <= size; i++) {
            if (storage[i] != null) {
                newStorage[counter] = storage[i];
                counter++;
            }
        }
        return newStorage;
    }


    public int size() {
        return size;
    }


    private int getIndex(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean exist(Resume r) {
        for (int i = 0; i < size; i++) {
            if (r.equals(storage[i])) {
                return true;
            }
        }
        return false;
    }
}

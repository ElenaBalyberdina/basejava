import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void update(Resume r) {
        //chek if resume is present
        if (exist(r)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(r)) {
                    storage[i] = r;
                }
            }
        }
    }

    void save(Resume r) {
        //chek if resume is not present
        if (!exist(r)) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("resume already exist");
        }

    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        //chek if resume is present
        if (!exist(get(uuid))) {
            System.out.println("resume is not exis");

        } else {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(storage[i].uuid, uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
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

    int size() {
        return size;
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

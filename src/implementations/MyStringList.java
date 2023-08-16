

public class MyStringList implements StringList {

        private String[] storage;
        private int capacity;
        private int count;

        public MyStringList(int capacity) {
            this.capacity = capacity;
            count = 0;
            storage = new String[capacity];
        }

        public int getCapacity() {
            return capacity;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String add(String item) {
            if (count >= capacity) {
                throw new MyStringListException("add: capacity is over");
            }
            checkItem(item);
            storage[count] = item;
            count++;
            return item;
        }

        private void checkItem(String item) {
            if (item == null) {
                throw new MyStringListException("item is null");
            }
        }

        private void checkIndexAndItem(int index, String item) {
            checkIndex(index);
            checkItem(item);

        }

        private void checkIndex(int index) {
            if (index < 0 || index >= count) {
                throw new MyStringListException("index invalid");
            }
        }

        private void checkCapacity() {
            if (count >= capacity) {
                throw new MyStringListException("capacity is over");
            }
        }

        @Override
        public String add(int index, String item) {
            String toReturn = item;
            checkCapacity();
            checkIndexAndItem(index + 1, item);
            IntStream.iterate(count - 1, i -> i >= index, i -> i - 1).forEach(i -> storage[i + 1] = storage[i]);
            count++;
            storage[index] = item;
            return toReturn;
        }

        @Override
        public String set(int index, String item) {
            checkIndexAndItem(index, item);
            storage[index] = item;
            return item;
        }

        @Override
        public String remove(String item) {
            checkItem(item);
            String result = "";
            int index = indexOf(item);
            if (index > 0) {
                IntStream.iterate(index, i -> i < count - 1, i -> i + 1).forEach(i -> storage[i] = storage[i + 1]);
                storage[count - 1] = null;
                count--;
                result = item;
            }
            return result;
        }

        @Override
        public String remove(int index) {
            checkIndex(index);
            String result = storage[index];

            return result;
        }

        @Override
        public boolean contains(String item) {
            checkItem(item);
            Set<String> storageSet = new HashSet<String>(Arrays.asList(storage));
            return storageSet.contains(item);
        }

        @Override
        public int indexOf(String item) {
            checkItem(item);
            int result = -1;
            if (!contains(item)) {
                return result;
            }
            ;
            result = 0;
            for (String s : storage
            ) {
                if (s.equals(item)) {
                    break;
                }
                result++;
            }
            return result;
        }

        @Override
        public int lastIndexOf(String item) {
            checkItem(item);
            int result = -1;
            if (!contains(item)) {
                return result;
            }
            for (result = count - 1; result > 0; result--) {
                if (storage[result].equals(item)) {
                    break;
                }
            }
            return result;
        }

        @Override
        public String get(int index) {
            checkIndex(index);
            return storage[index];
        }

        @Override
        public boolean equals(StringList otherList) {
            if (capacity != otherList.size()) return false;

            for (int i = 0; i < capacity; i++) {
                if (!storage[i].equals(otherList.get(i))) {
                    return false;
                }
                ;
            }
            return true;
        }

        @Override
        public int size() {
            return capacity;
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }

        @Override
        public void clear() {
            for (String s : storage) {
                s = null;
            }
            count = 0;
        }

        @Override
        public String[] toArray() {
            String[] newStorage = new String[capacity];
            newStorage = storage;
            return newStorage;
        }
    }


public class ArrayStack<K> implements Stack<K> {


        /**
         * Array of keys. Only the first size elements are part of this stack.
         * The stack grows toward larger indices.
         */
        private K[] keys;

        /**
         * Number of keys currently in this stack.
         */
        private int size;

        public ArrayStack() {
            keys = (K[]) new Object[1];
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public K pop() {
            size--;
            return keys[size];
        }

        @Override
        public void push(K key) {
            expandIfNecessary();
            keys[size] = key;
            size++;
        }

        /**
         * If this stack is full, copies the keys into an array twice as large.
         */
        private void expandIfNecessary() {
            if (size == keys.length) {
                K[] stretched = (K[]) new Object[size * 2];
                for (int i = 0; i < size; i++) {
                    stretched[i] = keys[i];
                }
                keys = stretched;
            }
        }

    }


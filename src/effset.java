public class effset implements Set<Integer> {

//everything is an object except the 8 primative types for each of those primitave types there is a coresponding wrapper class
//direct adressing table
// this violates the liskov subsittuion prinicple
        /** Array of keys. Only the first size elements are part of this set. */
        private boolean[] keys;

        /** Number of keys currently in this set. */
        private int size;

        public effset() {
            this.keys = new boolean[100];
        }

        @Override
        public void add(Integer key) {
            if(!keys[key]){
                keys[key] = true;
                size++;
            }
        }

        @Override
        public boolean contains(Integer key) {
              return keys[(int)key];
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public void remove(Integer key) {
            if(keys[key]) {
                keys[key] = false;
                size--;
            }
        }

        /** If this set is full, copies the keys into an array twice as large. */


    }


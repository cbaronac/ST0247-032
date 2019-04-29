/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller12;

import java.util.Comparator;

public class Pair<F, S> implements Comparator<Pair>{

        public final F first;
        public final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public S getSecond() {
            return second;
        }

        public boolean equals(Pair otro) {
            return this.getSecond() == otro.getSecond();
        }

        public int compareTo(Pair otro) {
            if (this.equals(otro)) {
                return 0;
            } else if ((int) this.getSecond() > (int) otro.getSecond()) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.compareTo(o1);
        }



    }

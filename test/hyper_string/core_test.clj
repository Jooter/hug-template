(ns hyper-string.core-test
  (:require [clojure.test :refer :all]
            [hyper-string.core :refer :all]))

(deftest str-test
  (testing "simple string 1"
    (is (= (hstr #(apply str (into [] %))
                 {:s (fn [a _] (str "-" a "-"))}
                 ["a" :s:b:1] 
                 {:b "b"}) 
           "a-b-"))))

(deftest sql-str-test
  (testing "simple sql string 1"
    (is (= (hstr #(apply str (into [] %))
                 {:limit  
                  (fn [v _] 
                    (if v 
                      (str " limit " v)
                    v))
                  }
                 ["a" :limit:limit] 
                 {:limit 9}) 
           "a limit 9"))))

(deftest sql-str-test-2
  (testing "simple sql string 2"
    (is (= (hstr #(apply str (into [] %))
                 {:limit  
                  (fn [v _] 
                    (if v 
                      (str " limit " v)
                    v))
                  }
                 ["a" :limit:limit] 
                 {}) 
           "a"))))

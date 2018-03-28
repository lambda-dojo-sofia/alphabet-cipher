(ns clojure-cipher.core-test
  (:require [clojure-cipher.core :refer [encode decode lowercase]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :refer [char-alpha list-distinct]]
            [clojure.test.check.properties :refer [for-all]]))

(defspec encode-decode 1000
  (for-all [message (list-distinct char-alpha {:min-elements 2})
            secret! (list-distinct char-alpha {:min-elements 2})]
           (= (decode (encode message secret!) secret!)
              (map lowercase message))))

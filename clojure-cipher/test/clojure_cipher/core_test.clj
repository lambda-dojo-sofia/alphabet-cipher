(ns clojure-cipher.core-test
  (:require [clojure.test :refer :all]
            [clojure-cipher.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(clojure.test.check.clojure-test/defspec encode-decode 1000
  (prop/for-all [message (filter #(< (int \a) (int (Character/toLowerCase %)) (int \z)) (gen/not-empty gen/string-alphanumeric))
                 secret (filter #(< (int \a) (int (Character/toLowerCase %)) (int \z)) (gen/not-empty gen/string-alphanumeric))]
                (= (decode (encode message secret) secret) message)))


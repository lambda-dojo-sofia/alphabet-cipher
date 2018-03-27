(ns clojure-cipher.core-test
  (:require [clojure.test :refer :all]
            [clojure-cipher.core :refer [encode decode]]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(clojure.test.check.clojure-test/defspec encode-decode 10000
  (prop/for-all [message (gen/list-distinct gen/char-alpha {:min-elements 2})
                 secret (gen/list-distinct gen/char-alpha {:min-elements 2})]
                (= (decode (encode message secret) secret)
                   (map #(Character/toLowerCase %1) message))))

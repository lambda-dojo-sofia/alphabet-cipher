(ns clojure-cipher.core-test
  (:require [clojure.test :refer :all]
            [clojure-cipher.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [com.gfredericks.test.chuck.properties :as prop']))

(clojure.test.check.clojure-test/defspec encode-decode 1000
  (prop/for-all [message (filter #(< (int \a) (int (Character/toLowerCase %)) (int \z)) (gen/not-empty gen/string-alphanumeric))
                 secret (filter #(< (int \a) (int (Character/toLowerCase %)) (int \z)) (gen/not-empty gen/string-alphanumeric))]
                (= (decode (encode message secret) secret) message)))

;; (clojure.test.check.clojure-test/defspec encode-decode 2
;;   (prop'/for-all [message (gen/string-alphanumeric)
;;                  secret (gen/string-alphanumeric)]
;;                 (let [m (filter #(< (int \a) (int (Character/toLowerCase %)) (int \z)) message)
;;                       s "fake"]
;;                   (= (decode (encode m s) s) m))))


(deftest a-test
  (testing "FIXME, I fail."
    (is (= (cipher \v \m) \h))))

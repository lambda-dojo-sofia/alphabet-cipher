(ns clojure-cipher.core)

(def dict {
   \a "abcdefghijklmnopqrstuvwxyz"
   \b "bcdefghijklmnopqrstuvwxyza"
   \c "cdefghijklmnopqrstuvwxyzab"
   \d "defghijklmnopqrstuvwxyzabc"
   \e "efghijklmnopqrstuvwxyzabcd"
   \f "fghijklmnopqrstuvwxyzabcde"
   \g "ghijklmnopqrstuvwxyzabcdef"
   \h "hijklmnopqrstuvwxyzabcdefg"
   \i "ijklmnopqrstuvwxyzabcdefgh"
   \j "jklmnopqrstuvwxyzabcdefghi"
   \k "klmnopqrstuvwxyzabcdefghij"
   \l "lmnopqrstuvwxyzabcdefghijk"
   \m "mnopqrstuvwxyzabcdefghijkl"
   \n "nopqrstuvwxyzabcdefghijklm"
   \o "opqrstuvwxyzabcdefghijklmn"
   \p "pqrstuvwxyzabcdefghijklmno"
   \q "qrstuvwxyzabcdefghijklmnop"
   \r "rstuvwxyzabcdefghijklmnopq"
   \s "stuvwxyzabcdefghijklmnopqr"
   \t "tuvwxyzabcdefghijklmnopqrs"
   \u "uvwxyzabcdefghijklmnopqrst"
   \v "vwxyzabcdefghijklmnopqrstu"
   \w "wxyzabcdefghijklmnopqrstuv"
   \x "xyzabcdefghijklmnopqrstuvw"
   \y "yzabcdefghijklmnopqrstuvwx"
   \z "zabcdefghijklmnopqrstuvwxy"})

(defn char->idx [c]
  (- (int c) (int \a)))

(defn cipher [x y]
  (let [idx (char->idx y)
        row (get dict x)]
    (nth row idx)))

(defn decipher [x y]
  (get "abcdefghijklmnopqrstuvwxyz" (clojure.string/index-of (get dict y) x)))

(defn translate [fun message key-word]
  (apply str (map #(fun (Character/toLowerCase %1) (Character/toLowerCase %2))
                  (remove #(Character/isWhitespace %) message)
                  (cycle key-word))))

(defn encode [message key-word]
  (translate cipher message key-word))

(defn decode [message key-word]
  (translate decipher message key-word))

;; (decode "h" "v") ;; => m

;; (encode "Meetmeontuesdayeveningatseven" "vigilance")

;; (decode (encode "meetmeontuesdayeveningatseven" "vigilance") "vigilance")

(defn -main [action message key-word]
  (println
   (cond
     (= action "encode") (encode message key-word)
     (= action "decode") (decode message key-word)
     :else "Invalid usage")))

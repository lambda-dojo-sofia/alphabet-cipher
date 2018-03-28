(ns clojure-cipher.core)

(defn lowercase [c]
  (Character/toLowerCase c))

(defn whitespace [c]
  (Character/isWhitespace c))

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

(defn cipher [x y]
  (let [idx (- (int y) (int \a))
        row (get dict x)]
    (nth row idx)))

(defn decipher [x y]
  (get "abcdefghijklmnopqrstuvwxyz" (clojure.string/index-of (get dict y) x)))

(defn translate [func message key-word]
  (map #(func (lowercase %1) (lowercase %2))
       (remove whitespace message)
       (cycle key-word)))

(defn encode [message key-word]
  (translate cipher message key-word))

(defn decode [message key-word]
  (translate decipher message key-word))

(defn -main [action message key-word]
  (println
   (apply str
          (cond
             (= action "encode") (encode message key-word)
             (= action "decode") (decode message key-word)
             :else "Invalid usage"))))

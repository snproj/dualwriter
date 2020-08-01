(ns dualwriter.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn insert-brs [string]
  (->> string
       str/split-lines
       (interpose "<br>")
       (apply str)))

(defn div-class-wrapper [class string]
  (str "<div class=\"" class "\">" string "</div>"))

(defn gen-str-comment [string]
  (str "<!--" string "-->"))

(defn whole-file-split [whole-file-string]
  (rest (str/split whole-file-string #"\\\\")))

(defn gen-str-first [string-content]
  (div-class-wrapper "first" string-content))

(defn gen-str-second [string-content]
  (div-class-wrapper "second" string-content))

(defn gen-str-simul [string-content]
  (div-class-wrapper "simul" string-content))



(defn duet-split [string-content]
  (rest (str/split string-content #"@@")))

(defn gen-str-first-duet [string-content]
  (div-class-wrapper "itemFirst" string-content))

(defn gen-str-second-duet [string-content]
  (div-class-wrapper "itemSecond" string-content))

(defn duet-section-directory [duet-section]
(let [first-char (first duet-section)
      string-content (subs duet-section 1)]
  (cond
    (= first-char \a) (gen-str-first-duet string-content)
    (= first-char \b) (gen-str-second-duet string-content)
    (= first-char \c) (gen-str-comment string-content)
    (= first-char \C) ""
    (= first-char nil) ""
    :else (throw (Exception. "DUET-SECTION-DIRECTORY COND ERROR")))))

(defn parse-duet [whole-file-section]
  (->> whole-file-section
       duet-split
       (map duet-section-directory)
       (apply str)
       (div-class-wrapper "container")))

(defn whole-file-section-directory [whole-file-section]
  (let [first-char (first whole-file-section)
        string-content (subs whole-file-section 1)]
    (cond
      (= first-char \1) (gen-str-first string-content)
      (= first-char \2) (gen-str-second string-content)
      (= first-char \s) (gen-str-simul string-content)
      (= first-char \d) (parse-duet string-content)
      (= first-char \c) (gen-str-comment string-content)
      (= first-char \C) ""
      (= first-char nil) ""
      :else (throw (Exception. "WHOLE-FILE-SECTION-DIRECTORY COND ERROR")))))

(defn parse-whole-file [whole-file-string]
  (->> whole-file-string
       whole-file-split
       (map whole-file-section-directory)
       (map insert-brs)
       (apply str)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [source-file-string (slurp (nth args 0))
        parse-output (parse-whole-file source-file-string)
        head-file-string (slurp (nth args 1))
        tail-file-string (slurp (nth args 2))
        final-output (str head-file-string parse-output tail-file-string)
        output-file-name (nth args 3)]
    (spit output-file-name final-output)))
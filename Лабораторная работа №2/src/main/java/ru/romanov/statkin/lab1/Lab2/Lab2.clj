(ns ru.romanov.statkin.lab1.Lab2.Lab2)


(defn func [inp]
      (loop [ acc [] [cnt & rest] inp ]
            (let [head (take cnt rest) tail (drop cnt rest)]
                 (if (= (count rest) 0) acc
                                        (recur (conj acc (into [] head)) tail)))))


(def indata [3 4 0 2 1 2 2 4 5])


(print (func indata))

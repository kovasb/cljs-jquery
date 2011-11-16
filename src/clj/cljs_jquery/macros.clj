(ns cljs-jquery.macros)



(defmacro $ [query & args]
  (let [queryobject (reverse (conj
 '(cond)
 `(= :this ~query)
 '(cljs-jquery.core/jquery (js* "this"))
 `(or (vector? ~query) (keyword? ~query))
 `(cljs-jquery.core/dom-create ~query)
 `true
 `(cljs-jquery.core/jquery ~query)
 ))   
        ]
    (reduce (fn [x y] `(cljs-jquery.core/call-jquery ~x ~(vec (concat [(name (first y))] (rest y))))) queryobject args)
    )
)  

;(defmacro $ [query & args]
;  (let [
;        queryobject
;        `(cond
;          (= :this ~query)
;          (js* "this")
;           (or (vector? ~query) (keyword? ~query))
;           (cljs-jquery.core/dom-create ~query)
;           true
;           (cljs-jquery.core/jquery ~query))]
;    (reduce (fn [x y] `(cljs-jquery.core/call-jquery ~x ~(vec (concat [(name (first y))] (rest y))))) queryobject args)
;    )
;)  

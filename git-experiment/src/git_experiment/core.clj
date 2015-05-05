(ns git-experiment.core)

(defn -main [& args]

    (defn greetings
      [name]
      (str "Greetings " name))

    (println (greetings "outlander"))
    (defn read-lines [filename]
        (with-open [rdr (clojure.java.io/reader filename)]
              (doall (line-seq rdr)))
      )

    (defn make-node [line]
        (reduce conj (map hash-map [:vertex1 :vertex2] (.split line " "))))

    (def node_map (map make-node (read-lines "edges")))



    ;(defrecord Node [foo bar baz])

    ;(defn node [foo bar baz] (loom.core.Node foo bar baz))


    (defn add-node [g n]
      (if (g n)
        g
        (assoc g n {:next #{} :prev #{}})))

    (defn add-edge [g n1 n2]
      (-> g
          (add-node n1)
          (add-node n2)
          (update-in [n1 :next] conj n2)
          (update-in [n2 :prev] conj n1)))

    (defn remove-edge [g n1 n2]
      (-> g
          (add-node n1)
          (add-node n2)
          (update-in [n1 :next] disj n2)
          (update-in [n2 :prev] disj n1)))

    (defn remove-node [g n]
      (if-let [{:keys [next prev]} (g n)]
        ((comp
          #(dissoc % n)
          #(reduce (fn [g* n*] (remove-edge g* n* n)) % prev)
          #(reduce (fn [g* n*] (remove-edge g* n n*)) % next))
         g)
        g))

    (defn contains-node? [g n]
      (g n))

    (defn contains-edge? [g n1 n2]
      (get-in g [n1 :next n2]))

    (defn next-nodes [g n]
      (get-in g [n :next]))

    (defn depth-first-search [g root-node goal?]
      (loop [open-list (list root-node)]
        (when-first [n open-list]
          (if (goal? n)
            n
            (recur (concat (next-nodes g n) (rest open-list)))))))




    (defn read-lines [filename]
        (with-open [rdr (clojure.java.io/reader filename)]
              (doall (line-seq rdr))))

    (defn make-node [line]
        ;(println (.split line " "))
        ;(list (.split line " "))
        (reduce conj (map hash-map [:vertex1 :vertex2] (.split line " "))))
      ;)


    (def grafo {} )
    (defn make-graph
      ([gra nodemarinheiro]
        (add-edge gra (get nodemarinheiro :vertex1) (get nodemarinheiro :vertex2)))
      ([nodemarinheiro]
        (add-edge grafo (get nodemarinheiro :vertex1) (get nodemarinheiro :vertex2)))

      )

    ;(println (map make-node (read-lines "edges")))
    ;(reduce println
    (def node_map (reduce make-graph (map make-node (read-lines "edges"))))
    (println node_map)


    ;(def ble (add-edge the-graph 64 48))
    ;(add-edge the-graph 64 49)

    ;(println ble)
    ;(def aux_map (int-array (range 100)))
    ;(def all_graph_paths_df (map  (fn [arg] (depth-first-search node_map 1 arg)) aux_map))
    ;(println all_graph_paths_df)




)

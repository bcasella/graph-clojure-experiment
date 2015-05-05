(defproject git-experiment "0.1.0-SNAPSHOT"
  :description "Learning graph with clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev
             {:dependencies [[org.clojure/clojure "1.5.1"]
                             [org.clojure/test.check "0.5.7"]
                             [liberator "0.12.2"]]}}
  :main git-experiment.core
)

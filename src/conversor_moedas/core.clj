(ns conversor-moedas.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-http.client :as http-client]
  )
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "usd"]
   ["-p" "--para moeda destino" "moeda a qual queremos saber o valor"]
  ]
)

(def chave (System/getenv "CHAVE_API"))

(def api-url "https://free.currencyconverterapi.com/api/v6/convert")

(defn parametrizar-moedas [de para]
  (str de "_" para)
)

(defn -main
  [& args]
  (let [{:keys [de para]} (:options (parse-opts args opcoes-do-programa))]
    
    (prn "Cotação: " (http-client/get api-url {:query-params {"q" (parametrizar-moedas de para)
                                                              "apiKey" chave
                                                              }
                                              }
                      )
    )
  )
)

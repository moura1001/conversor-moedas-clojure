(ns conversor-moedas.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [conversor-moedas.formatador-de-exibicao :refer [formatar]]
            [conversor-moedas.cambista :refer [obter-cotacao]]
  )
  (:gen-class))

(def opcoes-do-programa
  [["-d" "--de moeda base" "moeda base para conversão" :default "usd"]
   ["-p" "--para moeda destino" "moeda a qual queremos saber o valor"]
  ]
)

(defn- interpretar-opcoes [argumentos]
  (:options (parse-opts argumentos opcoes-do-programa))
)

(defn -main
  [& args]
  (let [{de :de para :para} (interpretar-opcoes args)]
    
    (->
      (obter-cotacao de para)
      (formatar de para)
      (prn)
    )
  )
)

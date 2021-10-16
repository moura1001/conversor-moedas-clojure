(ns conversor-moedas.core
  (:require [conversor-moedas.formatador-de-exibicao :refer [formatar]]
            [conversor-moedas.cambista :refer [obter-cotacao]]
            [conversor-moedas.interpretador-de-opcoes :refer [interpretar-opcoes]]
  )
  (:gen-class))

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

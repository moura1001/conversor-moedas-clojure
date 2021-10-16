(ns conversor-moedas.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-http.client :as http-client]
            [cheshire.core :refer [parse-string]]
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

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)]
    (->
      (:body 
        (http-client/get api-url {:query-params {"q" "USD_BRL"
                                                  "apiKey" chave
                                                }
                                  }
        )
      )

      (parse-string)
      (get-in ["results" moedas "val"])
    )
  )
)

(defn- formatar [cotacao de para]
  (str "1 " de " equivale a " cotacao " em " para)
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

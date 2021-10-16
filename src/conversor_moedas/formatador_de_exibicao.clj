(ns conversor-moedas.formatador-de-exibicao)

(defn formatar [cotacao de para]
  (str "1 " de " equivale a " cotacao " em " para)
)
var quantity = 0;

function addDiscount(){
    quantity++;
    document.getElementById('discounts').innerHTML += ''
    + '<div class="discount">'
    + '<div class="mb-3">'
    + '<label for="nomeDesconto'+ quantity +'" class="form-label">Nome</label>'
    + '<input type="text" class="form-control" name="discount-name'+ quantity +'" id="nomeDesconto'+ quantity +'" placeholder="Nome do Desconto">'
    + '</div>'
    + ''
    + ''
    + '<div class=" mb-3">'
    + '<label for="valorDesconto'+ quantity +'" class="form-label">Valor</label>'
    + '<input type="text" class="form-control" name="discount-value'+ quantity +'" id="valorDesconto'+ quantity +'" placeholder="R$666,69">'
    + '</div>'
    + ''
    + '<div class="mb-3">'
    + '<label for="percentual-type'+ quantity +'">Percentual</label>'
    + '<input type="radio" class="form-check-input" name="discount-type'+ quantity +'" id="percentual-type'+ quantity +'">'
    + '<label for="fixed-type'+ quantity +'" class="ms-3">Fixo</label>'
    + '<input type="radio" class="form-check-input" name="discount-type'+ quantity +'" id="fixed-type'+ quantity +'">'
    + '</div>'
    + '</div>'
}
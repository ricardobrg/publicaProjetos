var quantity = 0;

document.getElementById("addDiscount").addEventListener('click', (e) => {
    e.preventDefault();
    quantity++;
    window.sessionStorage.setItem('discounts', quantity)
    let field = document.createElement('div');
    field.className = "discount";
    field.innerHTML ='<div class="mb-3">'
    + '<label for="nomeDesconto'+ quantity +'" class="form-label">Nome</label>'
    + '<input type="text" class="form-control" name="discount-name'+ quantity +'" id="nomeDesconto'+ quantity +'" placeholder="Nome do Desconto">'
    + '</div>'
    + ''
    + ''
    + '<div class=" mb-3">'
    + '<label for="valorDesconto'+ quantity +'" class="form-label">Valor</label>'
    + '<input type="text" class="form-control" name="discount-value'+ quantity +'" id="valorDesconto'+ quantity +'" placeholder="200 (fixo) 5 (percentual)">'
    + '</div>'
    + ''
    + '<div class="mb-3">'
    + '<label for="percentual-type'+ quantity +'">Percentual</label>'
    + '<input type="radio" class="form-check-input" name="discount-type'+ quantity +'" id="percentual-type'+ quantity +'" value=1>'
    + '<label for="fixed-type'+ quantity +'" class="ms-3">Fixo</label>'
    + '<input type="radio" class="form-check-input" name="discount-type'+ quantity +'" id="fixed-type'+ quantity +'" value=0>'
    + '</div>'
    document.getElementById('discounts').appendChild(field);
})
<h1>InovaX</h1>

<h1>Integrantes:</h1>
Lucas dos Santos Lopes RM:550790<br>
Murilo Machado RM:550718<br> 
Victor Taborda Rodrigues RM:97900<br>
Gustavo Marques Catelan RM:551823<br> 
Gabriel Bacelar Valentim RM97901

<h2>DESCRIÇÃO DO PROJETO</h2>
A InovaX é uma plataforma inovadora desenvolvida para comparar produtos e serviços essenciais, como planos de saúde e assinaturas, além de itens convencionais. 
Nosso objetivo é oferecer uma experiência completa de comparação, facilitando a tomada de decisões com base nas melhores opções disponíveis em diversas plataformas.

<h2>Funcionalidades Principais</h2>
Pesquisa Avançada de Produtos: Utilizando a linguagem Kotlin e a SerpAPI para integração com o Google Shopping, é possível buscar produtos e visualizar uma lista de opções com preços e fornecedores variados, permitindo ao usuário encontrar a melhor opção.<br>
Gestão de Carrinho de Compras: O usuário pode adicionar produtos ao carrinho com as melhores ofertas que encontrou, visualizar o valor total dos itens selecionados e remover produtos conforme sua preferência.<br>
Comparação de Ofertas: Cada produto pesquisado exibe diferentes preços e sites, facilitando a escolha da oferta ideal.<br>

<h2>Armazenamento Local com Async Storage</h2>
O aplicativo utiliza o SharedPreferences do Android para armazenamento local, atendendo ao requisito de Async Storage. As principais implementações incluem:<br>
Carrinho de Compras: O estado do carrinho é salvo localmente, permitindo que o usuário veja os itens adicionados, mesmo após fechar e reabrir o aplicativo.<br>
Cadastro de Usuário: Dados de cadastro, como nome e senha, são armazenados em `SharedPreferences`, possibilitando persistência de dados do usuário entre as sessões.<br>

<h2>CRUD Completo nas Telas</h2>
Create (Adicionar Produto): A funcionalidade de adicionar produtos ao carrinho está implementada. O método addToCart na classe Pesquisa permite ao usuário incluir um produto no carrinho e salva esses dados localmente via SharedPreferences (usando CartUtils). <br>
Read (Ler Produtos): Os produtos são exibidos na tela de pesquisa com a opção de visualizar os detalhes do produto. Essa leitura é feita a partir da pesquisa via API (Google Shopping usando SerpAPI), e os resultados são mostrados no ResultAdapter. O carrinho também exibe os produtos armazenados. <br>
Update (Atualizar o Total do Preço no Carrinho de Compras): O total do carrinho é atualizado automaticamente conforme produtos são adicionados ou removidos, refletindo o valor atual dos itens. <br>
Delete (Remover Produto): A remoção de produtos do carrinho está implementada. O método removeFromCart na classe Carrinho permite ao usuário excluir itens do carrinho e atualiza o armazenamento local.Pesquisa via API: A integração com a API (Google Shopping via SerpAPI) está presente no método searchProducts da classe Pesquisa. Essa função faz uma chamada para obter dados de produtos, que são exibidos ao usuário. <br>

<h2>Tecnologias Utilizadas</h2>
Linguagem: Kotlin<br>
API de Pesquisa: SerpAPI (Google Shopping)

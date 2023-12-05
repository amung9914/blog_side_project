const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', event =>{
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`,{
            method: 'DELETE'
        })
            .then(()=>{
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            })
    })
}

// 수정기능
const modifyButton = document.getElementById('modify-btn');
if(modifyButton){
    modifyButton.addEventListener('click', event =>{
        // 현재 페이지의 URL에서 쿼리 문자열을 추출한다. 그리고 'id'파라미터의 값을 가져온다.
       let params = new URLSearchParams(location.search);
       let id = params.get('id');

       fetch(`/api/articles/${id}`,{
           method: 'PUT',
           headers: {
               "Content-Type": "application/json",
           },
           body: JSON.stringify({
               title: document.getElementById('title').value,
               content: document.getElementById('content').value
           })
       })
           .then(()=>{
               alert('수정이 완료되었습니다.');
               location.replace(`/articles/${id}`);
           })
    });
}
// 생성기능
const createButton = document.getElementById('create-btn');
if(createButton){
    createButton.addEventListener('click',event => {
        fetch("/api/articles",{
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            }),
        }).then(()=>{
            alert('등록이 완료되었습니다.')
            location.replace("/articles");
        });
    });
}
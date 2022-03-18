function queryByName(username){
    let queryName=prompt('請輸入要查詢的名字 :', username);
    if(queryName.length==0){
        alert('查詢名字不可空白');
        return;
    }
    //alert('你好 :'+queryName);
    let path='/sts/user/querybyname/'+queryName;
    window.location.href=path;
}

function queryByAge(age){
	let queryAge=prompt('請輸入要查詢的年齡圍範 :', age);
    if(queryAge.length==0){
        alert('查詢年齡不可空白');
        return;
    }   
    let path='/sts/user/querybyage/'+queryAge;
    window.location.href=path;
}

function queryByMaxId(){
	let path='/sts/user/querybymaxid/';
    window.location.href=path;	
}

function queryByCountId(){
	let path='/sts/user/countid/';
    window.location.href=path;	
}

function queryByPagination(){
	let path='/sts/page/';
    window.location.href=path;
}

function queryByLikeNameLess(){
    let qtyLikeName=document.getElementById('likename').value;
    let qtyId=document.getElementById('qtyid').value;
    if (qtyLikeName.length==0 || qtyId.length==0){
        alert('開頭名稱或序號不可空白');
        return;
    }
    let path='/sts/user/querylikeless?likename='+qtyLikeName+'&id='+qtyId;
    //alert('Path :'+path);
    window.location.href=path;
}

function queryByLikeNameThan(){
    let qtyLikeName=document.getElementById('likename').value;
    let qtyId=document.getElementById('qtyid').value;
    if (qtyLikeName.length==0 || qtyId.length==0){
        alert('開頭名稱或序號不可空白');
        return;
    }
    let path='/sts/user/querylikethan?likename='+qtyLikeName+'&id='+qtyId;
    //alert('Path :'+path);
    window.location.href=path;
}

function qtyMultipleIds(){
	let qtyMultipleIds=document.querySelector('#qtyId').value;
	let qtyBirth=document.getElementById('qtyDate').value;
	//alert('Ids :'+ qtyMultipleIds);
	//alert('Birth :'+qtyBirth);
	if(qtyMultipleIds.length==0 || qtyBirth.length==0){
		alert('序號或是生日不可空白');				
	}
}

function qtyStarAndEndBirth(){
	let qtyBirth=document.getElementById('qtyDate').value;
	let qtyBirth2=document.getElementById('qtyDate2').value;
	if(qtyBirth.length==0 || qtyBirth2.length==0){
		alert('生日不可空白');
	}
}

function backtoselect(){
	let path='/sts/user/';
	window.location.href=path;
}
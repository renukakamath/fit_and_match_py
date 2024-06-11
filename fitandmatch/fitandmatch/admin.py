from flask import *
from database import *
import uuid
admin=Blueprint('admin',__name__)
@admin.route('/adminhome',methods=['post','get'])
def adminhome():
	return render_template("adminhome.html")



@admin.route('/viewusers',methods=['post','get'])
def viewusers():
	data={}
	q="select *,concat(cust_fname,' ',cust_lname)as Name from customers_table"
	res=select(q)
	data['view']=res
	return render_template("adminviewusers.html",data=data)
@admin.route('/manage_salesteam',methods=['get','post'])
def manage_salesteam():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from salesteam_table where sales_id='%s'"%(id)
		delete(q)
	if 'submit' in request.form:
		name=request.form['sales_name']
		city=request.form['sales_city']
		pincode=request.form['sales_pincode']
		email=request.form['sales_email']
		contact=request.form['sales_contact']
		user=request.form['username']
		pas=request.form['password']

		q="insert into login_table values(null,'%s','%s','salesteam')"%(user,pas)
		id=insert(q)
		q="insert into salesteam_table values(null,'%s','%s','%s','%s','%s','%s')"%(id,name,city,pincode,email,contact)
		id=insert(q)
		if id:
			flash('Submitted Successfully !!!')
	q="select * from salesteam_table"
	res=select(q)
	data['view']=res
	return render_template("adminsalesteam.html",data=data)
@admin.route('/manage_logistics',methods=['get','post'])
def manage_logistics():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from logistics_table where logi_id='%s'"%(id)
		delete(q)
	if 'submit' in request.form:
		name=request.form['logi_name']
		email=request.form['logi_email']
		contact=request.form['logi_contact']
		user=request.form['username']
		pas=request.form['password']

		q="insert into login_table values(null,'%s','%s','logistics')"%(user,pas)
		id=insert(q)
		q="insert into logistics_table values(null,'%s','%s','%s','%s')"%(id,name,email,contact)
		id=insert(q)
		if id:
			flash('Submitted Successfully !!!')
	q="select * from logistics_table"
	res=select(q)
	data['view']=res
	return render_template("adminmanage_logisticteam.html",data=data)
@admin.route('/manage_category',methods=['get','post'])
def manage_category():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from category_table where cat_id='%s'"%(id)
		delete(q)
	if 'submit' in request.form:
		catname=request.form['category']
		q="insert into category_table values(null,'%s')"%(catname)
		id=insert(q)
		if id:
			flash('Submitted Successfully !!!')
	q="select * from category_table"
	res=select(q)
	data['view']=res
	return render_template("adminmanagecategory.html",data=data)


	
@admin.route('/manage_products',methods=['get','post'])
def manage_products():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="delete":
		q="delete from products_table where pro_id='%s'"%(id)
		delete(q)
	if 'submit' in request.form:
		image=request.files['pro_img']
		path='static/uploads/'+str(uuid.uuid1())+image.filename
		image.save(path)
		cat_id=request.form['cat_id']
		name=request.form['pro_name']
		desc=request.form['pro_desc']
		price=request.form['pro_price']
		q="insert into products_table values(null,'%s','%s','%s','%s','%s')"%(cat_id,path,name,desc,price)
		id=insert(q)
		if id:
			flash('Submitted Successfully !!!')
	q="select * from category_table"
	res=select(q)
	data['category']=res
	q="select * from products_table INNER JOIN category_table USING(cat_id)"
	res=select(q)
	data['view']=res
	return render_template("adminmanageproducts.html",data=data)
@admin.route('/viewfeedback',methods=['get','post'])
def viewfeedback():
	data={}
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`) AS NAME FROM `feedback_table` INNER JOIN `customers_table` USING(cust_id)"
	res=select(q)
	data['view']=res
	return render_template("adminviewfeedback.html",data=data)
@admin.route('/viewsales',methods=['get','post'])
def viewsales():
	data={}
	q="SELECT * FROM `sales_table` INNER JOIN `salesteam_table` USING(sales_id) INNER JOIN `order_table` USING(order_id)"
	res=select(q)
	data['view']=res
	return render_template("adminviewsales.html",data=data)
@admin.route('/viewpayment',methods=['get','post'])
def viewpayment():
	data={}
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`) AS NAME FROM `payment_table` INNER JOIN `customers_table` USING(cust_id) INNER JOIN `products_table` USING(pro_id)"
	res=select(q)
	data['view']=res
	return render_template("adminviewpayment.html",data=data)

	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
@admin.route('/logout')
def logout():
	session.clear()
	return redirect(url_for('public.login'))

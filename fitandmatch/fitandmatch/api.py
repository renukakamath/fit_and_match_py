from flask import *
from database import *
import demjson
api=Blueprint('api',__name__)

@api.route('/login',methods=['get','post'])
def login():
	data={}
	username=request.args['username']
	password=request.args['password']
	q ="select * from login_table where username='%s' and password='%s'" % (username,password)
	res=select(q)
	if len(res)>0:
		data['status'] = 'success'
		data['data']=res
	else:
		data['status'] = 'failed'
	return demjson.encode(data)
@api.route('/registration',methods=['get','post'])
def registration():
	data={}
	firstname=request.args['firstname']
	lastname=request.args['lastname']
	gender=request.args['gen']
	hname=request.args['housename']
	city=request.args['place']
	pincode=request.args['pin']
	email=request.args['email']
	contact=request.args['phonenumber']
	username=request.args['uname']
	password=request.args['pass']
	q = "insert into login_table(username,password,type)values('%s','%s','user')" % (username,password)
	login_id = insert(q)
	q = "insert into customers_table values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')" % (login_id,firstname,lastname,gender,hname,city,pincode,email,contact)
	id=insert(q)
	if id>0:
		data['status'] = 'success'
	else:
		data['status'] = 'failed'
	return demjson.encode(data)

@api.route('/feedback',methods=['get','post'])
def feedback():
	data={}
	feedbacks=request.args['feedbacks']
	log_id=request.args['log_id']
	q = "insert into feedback_table values(null,(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s'),'%s',curdate())" % (log_id,feedbacks)
	id = insert(q)
	if id>0:
		data['status'] = 'success'
	else:
		data['status'] = 'failed'
	return demjson.encode(data)
@api.route('/view_categories',methods=['get','post'])
def view_categories():
	data={}
	q = "select * from category_table"
	res = select(q)
	if res:
		data['status'] = 'success'
		data['data']=res
	else:
		data['status'] = 'failed'
	return demjson.encode(data)
@api.route('/view_ornament',methods=['get','post'])
def view_ornament():
	data={}
	catid=request.args['catid']
	q = "select * from products_table inner join category_table using(cat_id) where products_table.cat_id='%s'"%(catid)
	res = select(q)
	if res:
		data['status'] = 'success'
		data['data']=res
	else:
		data['status'] = 'failed'
	return demjson.encode(data)

@api.route('/addcart',methods=['get','post'])
def addcart():
	data={}
	qty=request.args['qua']
	logid=request.args['logid']
	pid=request.args['pid']
	tot=request.args['tot']
	q = "insert into order_table values(null,(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s'),'%s','%s','cart','%s')" % (logid,pid,tot,qty)
	id = insert(q)
	q = "insert into orderstatus_table values(null,'%s','Pending')" % (id)
	id = insert(q)
	if id>0:
		data['status'] = 'success'
	else:
		data['status'] = 'failed'
	return demjson.encode(data)

@api.route('/view_cart',methods=['get','post'])
def view_cart():
	data={}
	logid=request.args['logid']
	q = "select * from products_table inner join order_table using(pro_id) where order_status='cart' and cust_id=(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s')"%(logid)
	res = select(q)
	if res:
		data['status'] = 'success'
		data['data']=res
		data['method'] = 'view_cart'
	else:
		data['status'] = 'failed'
		data['method'] = 'view_cart'
	return demjson.encode(data)


@api.route('/buy',methods=['get','post'])
def buy():
	data={}
	logid=request.args['logid']
	q = "select * from order_table where cust_id=(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s') and order_status='cart'" % (logid)
	res = select(q)
	if res:
		for row in res:
			q = "insert into payment_table values(null,'%s','%s','%s','%s','pending')" % (row['cust_id'],row['order_id'],row['pro_id'],row['tot_price'])
			id = insert(q)
		q = "update order_table set order_status='requested' where cust_id=(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s') and order_status='cart'" % (logid)
		c = update(q)
		data['status'] = 'success'
		data['method'] = 'buy'
	else:
		data['status'] = 'failed'
		data['method'] = 'buy'
	return demjson.encode(data)

@api.route('/track_order',methods=['get','post'])
def track_order():
	data={}
	logid=request.args['logid']
	q = "select * from products_table inner join order_table using(pro_id) inner join orderstatus_table using(order_id) where order_status!='cart' and cust_id=(SELECT `cust_id` FROM `customers_table` WHERE `log_id`='%s')"%(logid)
	res = select(q)
	if res:
		data['status'] = 'success'
		data['data']=res
		data['method'] = 'track_order'
	else:
		data['status'] = 'failed'
		data['method'] = 'track_order'
	return demjson.encode(data)
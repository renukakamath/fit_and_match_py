from flask import *
from database import *
logistics=Blueprint('logistics',__name__)
@logistics.route('/',methods=['post','get'])
def logisticshome():
	return render_template("logisticshome.html")
@logistics.route('/viewconforders',methods=['post','get'])
def viewconforders():
	data={}
	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None
	if action=="view":
		q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`)AS NAME FROM  `customers_table` where cust_id='%s'"%(id)
		res=select(q)
		data['custdetails']=res
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`)AS NAME FROM `order_table` INNER JOIN `customers_table` USING(`cust_id`) INNER JOIN  `products_table` USING(`pro_id`) where order_status='confirmed'"
	res=select(q)
	data['view']=res
	return render_template("logisticsviewconforders.html",data=data) 
@logistics.route('/updorderstatus',methods=['post','get'])
def updorderstatus():
	data={}
	q="SELECT *,CONCAT(`cust_fname`,' ',`cust_lname`)AS NAME,orderstatus_table.status as cstatus FROM `order_table` INNER JOIN `customers_table` USING(`cust_id`) INNER JOIN  `products_table` USING(`pro_id`) INNER join orderstatus_table USING(order_id) where order_status='confirmed'"
	res=select(q)
	data['view']=res
	for i in range(1,len(res)+1):
		if 'submit'+str(i) in request.form:
			status=request.form['status'+str(i)]
			order_id=request.form['orderid'+str(i)]
			q="update orderstatus_table set status='%s' where order_id='%s'"%(status,order_id)
			c=update(q)
			if c>0:
				flash('Updated successfully !!!')
				return redirect(url_for('logistics.updorderstatus'))
	return render_template("logisticsupdorderstatus.html",data=data)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
@logistics.route('/logout')
def logout():
	session.clear()
	return redirect(url_for('public.login'))

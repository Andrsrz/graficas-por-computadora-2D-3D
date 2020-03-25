import os
import tkinter
from tkinter import *

def Linea():
	os.system('javaw Lineas_Mask')
	os.popen()

def Lineas():
	#os.system('javac lineas.java')
	os.system('javaw Lineas_An')
	os.popen()	

def Lineas_dda():
	os.system('javaw Circle_Mask')
	os.popen()

def Lineas_Bres():
	os.system('javaw Circle_An')
	os.popen()

ventana = Tk()
ventana.geometry("400x300+50+50")
ventana.title("programas java")

etiqueta = Label(ventana)
etiqueta2 = Label(ventana)
btn = Button(etiqueta, text="Line mascara", command=Linea, height = 2, width = 15)
btn1 = Button(etiqueta, text="Linea ancha", command=Lineas, height = 2, width = 15)
#btn2 = Button(etiqueta, text="Lineas_dda", command=Lineas_dda, height = 2, width = 15)

btn3 = Button(etiqueta2, text="Circulo mascara", command=Lineas_dda, height = 2, width = 15)
btn5 = Button(etiqueta2, text="Circulo ancho", command=Lineas_Bres, height = 2, width = 15)
#btn4 = Button(etiqueta2, text="Lineas_Bres_cubo", command=Lineas_Bres_cubo, height = 2, width = 15)

etiqueta.pack( side=TOP )
btn.pack(padx=5, pady=10, side=LEFT)
btn1.pack(padx=5, pady=10, side=RIGHT)
etiqueta2.pack( side=TOP )
btn3.pack(padx=5, pady=10, side=LEFT)
btn5.pack(padx=5, pady=10, side=RIGHT)

ventana.mainloop()
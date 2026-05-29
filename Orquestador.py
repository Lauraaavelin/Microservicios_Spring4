import requests
import time


IP_CLIENTE = "13.222.179.241"
IP_PROYECTO = "100.54.217.88"
IP_REPORTES = "52.87.172.29"
IP_FACTURA = "52.90.53.157"

def ejecutar_orquestacion():
    print("--- INICIANDO FLUJO DE ORQUESTACIÓN BITE.CO ---")
    
    #Crear Cliente
    print("\n[Paso 1] Solicitando creación de cliente...")
    url_cliente = f"http://{IP_CLIENTE}:8000/api/clientes/"
    payload_cliente = {"nombre": "Cliente Nuevo S.A.", "nit": "900123456-1"} 
    
    res_cliente = requests.post(url_cliente, json=payload_cliente)
    if res_cliente.status_code != 201:
        print("ERROR: Falló la creación del cliente. Deteniendo flujo.")
        return
    
    cliente_id = res_cliente.json().get("id", 99) 
    print(f"Éxito: Cliente creado con ID: {cliente_id}")


    # Crear Info de Proyecto
    print("\n[Paso 2] Solicitando creación del proyecto...")
    url_proyecto = f"http://{IP_PROYECTO}:8000/api/proyectos/"
    payload_proyecto = {"cliente_id": cliente_id, "nombre_proyecto": "Migración Cloud"}
    
    res_proyecto = requests.post(url_proyecto, json=payload_proyecto)
    if res_proyecto.status_code != 201:
        print("ERROR: Falló la creación del proyecto. Deteniendo flujo.")
        return
        
    proyecto_id = res_proyecto.json().get("id", 88)
    print(f"Éxito: Proyecto creado con ID: {proyecto_id}")


    # Crear 4 Reportes e intentar guardarlos en BD correspondientes
    print("\n[Paso 3] Solicitando la generación de los 4 reportes financieros...")
    url_reportes = f"http://{IP_REPORTES}:8000/api/reportes/generar/"
    payload_reportes = {"proyecto_id": proyecto_id, "cantidad_reportes": 4}
    
    res_reportes = requests.post(url_reportes, json=payload_reportes)
    if res_reportes.status_code != 200:
        print("ERROR: Falló la generación de reportes masivos. Deteniendo flujo.")
        return
    print("Éxito: Los 4 reportes se procesaron y persistieron en la base de datos.")


    # Generar Factura de un Cliente
    print("\n[Paso 4] Generando factura final del cliente...")
    url_factura = f"http://{IP_FACTURA}:8000/api/facturas/generar"
    payload_factura = {"cliente_id": cliente_id, "monto": 5000}
    
    res_factura = requests.post(url_factura, json=payload_factura)
    if res_factura.status_code != 201:
        print("ERROR: Falló la facturación del servicio.")
        return
    print("Éxito: Factura emitida correctamente. Proceso completado con éxito.")

if __name__ == "__main__":
    start_time = time.time()
    ejecutar_orquestacion()
    print(f"\nTiempo total de ejecución del flujo: {time.time() - start_time:.2f} segundos")

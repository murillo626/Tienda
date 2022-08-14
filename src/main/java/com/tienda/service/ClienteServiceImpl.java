package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.dao.CreditoDao;
import com.tienda.domain.Cliente;
import com.tienda.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private CreditoDao creditoDao;
    
    public Cliente findByApellidos(Cliente cliente){
        return ((List<Cliente>)clienteDao.findByApellidos(cliente.getApellidos())).get(0);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>)clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        //Recupero el objeto crédito que trae el cliente
        Credito credito = cliente.getCredito();
        //"salvo" el objeto credito en la bd
        creditoDao.save(credito);
        
        cliente.setCredito(credito);
        
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {        
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
}
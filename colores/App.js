import React, { useState } from 'react';
import { StyleSheet, View, Text, TextInput } from 'react-native';

export default function App() {
  const [nif, setNif] = useState('');
  const [letra, setLetra] = useState('');

  const calcularLetraNIF = (nif) => {
    if (nif.length === 8) {
      const letras = 'TRWAGMYFPDXBNJZSQVHLCKE';
      const posicion = parseInt(nif) % 23;
      setLetra(letras.charAt(posicion));
    } else {
      setLetra('');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.label}>Introduce el NIF:</Text>
      <TextInput
        style={styles.input}
        maxLength={8}
        keyboardType="numeric"
        value={nif}
        onChangeText={(text) => {
          setNif(text);
          calcularLetraNIF(text);
        }}
      />

      <Text style={styles.labelResultado}>Letra del NIF:</Text>
      <TextInput style={styles.resultado} editable={false} value={letra} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 16,
    backgroundColor: '#ffffff', // Color de fondo
  },
  label: {
    fontSize: 18,
    marginBottom: 8,
    color: 'red', // Color del texto de la etiqueta
  },
  input: {
    height: 40,
    borderColor: 'blue',
    borderWidth: 1,
    marginBottom: 16,
    paddingHorizontal: 8,
    color: '#0066cc', // Color de los números
  },
  labelResultado: {
    fontSize: 18,
    marginBottom: 8,
    color: 'purple', // Color del texto de la etiqueta de resultado
  },
  resultado: {
    height: 40,
    borderColor: 'yellow',
    borderWidth: 1,
    paddingHorizontal: 8,
    backgroundColor: '#f0f0f0',
    fontSize: 18,
    color: '#008000', // Color de la letra
  },
});
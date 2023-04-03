from transformers import AutoTokenizer, AutoModelWithLMHead
import torch
if torch.cuda.is_available():
    device = torch.device("cuda")
else :
    device = "cpu"
    
tokenizer = AutoTokenizer.from_pretrained("beyond/genius-large-k2t")
model = AutoModelWithLMHead.from_pretrained("beyond/genius-large-k2t").to(device)

def get(input_query):
  input_ids = tokenizer.encode(input_query.lower(), return_tensors='pt').to(device)
  sample_outputs = model.generate(input_ids,
                                do_sample=True,
                                num_beams=1, 
                                max_length=1024,
                                temperature=0.99,
                                top_k = 10,
                                num_return_sequences=1)
  return tokenizer.decode(sample_outputs[0], skip_special_tokens=True)
tokenizer1 = AutoTokenizer.from_pretrained("Helsinki-NLP/opus-mt-en-zh")
model1 = AutoModelWithLMHead.from_pretrained("Helsinki-NLP/opus-mt-en-zh").to(device)

def get1(input_query):
  input_ids = tokenizer1.encode(input_query.lower(), return_tensors='pt').to(device)
  sample_outputs = model1.generate(input_ids,
                                do_sample=True,
                                num_beams=1, 
                                max_length=1024,
                                temperature=0.99,
                                top_k = 10,
                                num_return_sequences=1)
  return tokenizer1.decode(sample_outputs[0], skip_special_tokens=True)
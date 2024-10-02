import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Certificat} from "../../classes/Certificat/certificat";

@Injectable({
  providedIn: 'root'
})
export class CertificatService {

  private url = 'http://localhost:8081/api/certificat/'

  constructor(private httpClient:HttpClient) { }

  getCertificates(): Observable<Certificat[]> {
    return this.httpClient.get<Certificat[]>(this.url + 'all');
  }

  // Get certificate by ID
  getCertificatById(id: number): Observable<Certificat> {
    return this.httpClient.get<Certificat>(`${this.url}${id}`);
  }

  // Generate a new certificate
  generateCertificat(userId: number | null, coursId: number | null, certificat: Certificat): Observable<Certificat> {
    return this.httpClient.post<Certificat>(`${this.url}generate/${userId}/${coursId}`, certificat);
  }

  // Get all certificates for a specific user
  getUtilisateurCertificatList(userId: number | null): Observable<Certificat[]> {
    return this.httpClient.get<Certificat[]>(`${this.url}my-certificates/${userId}`);
  }

  // Get all certificates for a specific course
  getCoursCertificatList(coursId: number): Observable<Certificat[]> {
    return this.httpClient.get<Certificat[]>(`${this.url}cours-certificates/${coursId}`);
  }

}

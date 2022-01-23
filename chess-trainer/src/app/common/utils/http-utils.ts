import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreateLineBody } from '../model/interface/create-line-body';
import { DeleteLineBody } from '../model/interface/delete-line-body';
import { DeleteMoveBody } from '../model/interface/delete-move-body';
import { PostMoveBody } from '../model/interface/post-move-body';

export class HttpUtils {
  static getPosition(lineUuid: string, fenPosition: string, http: HttpClient): Observable<Object> {
    const fenPositionToSend: string = this.replaceAll(fenPosition, '/', '_');
    return http.request('GET', `${environment.apiUrl}/position/${lineUuid}/${fenPositionToSend}`, {
      responseType: 'json',
    });
  }

  static postMoveEvent(body: PostMoveBody, http: HttpClient): Observable<Object> {
    return http.post(`${environment.apiUrl}/move`, body, {
      responseType: 'json',
    });
  }

  static deleteMove(body: DeleteMoveBody, http: HttpClient): Observable<Object> {
    return http.request('DELETE', `${environment.apiUrl}/move`, {
      responseType: 'json',
      body: body,
    });
  }

  static postCreateLine(body: CreateLineBody, http: HttpClient): Observable<Object> {
    return http.post(`${environment.apiUrl}/line/create`, body, {
      responseType: 'json',
    });
  }

  static getOpenings(http: HttpClient): Observable<any> {
    return http.get(`${environment.apiUrl}/openings`, {
      responseType: 'json',
    });
  }

  static deleteLine(body: DeleteLineBody, http: HttpClient): Observable<any> {
    return http.request('DELETE', `${environment.apiUrl}/line`, {
      responseType: 'json',
      body: body,
    });
  }

  static updatePosition(body: PostMoveBody, http: HttpClient): Observable<any> {
    return http.post(`${environment.apiUrl}/position`, {
      responseType: 'json',
      body: body,
    });
  }

  private static replaceAll(str: string, find: string, replace: string): string {
    return str.replace(new RegExp(this.escapeRegExp(find), 'g'), replace);
  }
  // Makes the string to find safer
  private static escapeRegExp(string: string) {
    return string.replace(/[.*+\-?^${}()|[\]\\]/g, '\\$&'); // $& means the whole matched string
  }
}
